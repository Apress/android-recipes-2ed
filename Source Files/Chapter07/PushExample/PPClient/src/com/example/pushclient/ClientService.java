package com.example.pushclient;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.ibm.mqtt.IMqttClient;
import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;
import com.ibm.mqtt.MqttPersistenceException;
import com.ibm.mqtt.MqttSimpleCallback;

public class ClientService extends Service implements MqttSimpleCallback {

    //Location where broker is running
    private static final String HOST = "192.168.2.4";
    private static final String PORT = "1883";
    //30 minute keep-alive ping
    private static final short KEEP_ALIVE = 60 * 30;
    //Unique identifier of this device
    private static final String CLIENT_ID = "apress/"+System.currentTimeMillis();
    //Topic we want to watch for
    private static final String TOPIC = "apress/examples";
    
    private static final String ACTION_KEEPALIVE = "com.examples.pushclient.ACTION_KEEPALIVE";
    
    private IMqttClient mClient;
    private AlarmManager mManager;
    private PendingIntent alarmIntent;
    
    @Override
    public void onCreate() {
        super.onCreate();
        mManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        
        Intent intent = new Intent(ACTION_KEEPALIVE);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        
        registerReceiver(mReceiver, new IntentFilter(ACTION_KEEPALIVE));
        
        try {
            //Format: tcp://hostname@port
            String connectionString = String.format("%s%s@%s", MqttClient.TCP_ID, HOST, PORT);
            mClient = MqttClient.createMqttClient(connectionString, null);
        } catch (MqttException e) {
            e.printStackTrace();
            //Can't continue without a client
            stopSelf();
        }
    }
    
    @Override
    public void onStart(Intent intent, int startId) {
        //Callback on Android devices prior to 2.0
        handleCommand(intent);
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Callback on Android devices 2.0 and later
        handleCommand(intent);
        //If Android kills this service, we want it back when possible
        return START_STICKY;
    }
    
    private void handleCommand(Intent intent) {
        try {
            //Make a connection
            mClient.connect(CLIENT_ID, true, KEEP_ALIVE);
            //Target MQTT callbacks here
            mClient.registerSimpleHandler(this);
            //Subscribe to a topic
            String[] topics = new String[] { TOPIC };
            //QoS of 0 indicates fire once and forget
            int[] qos = new int[] { 0 };
            mClient.subscribe(topics, qos);
            
            //Schedule a ping
            scheduleKeepAlive();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
        unscheduleKeepAlive();
        
        if(mClient != null) {
            try {
                mClient.disconnect();
                mClient.terminate();
            } catch (MqttPersistenceException e) {
                e.printStackTrace();
            }
            mClient = null;
        }
    }
   
    //Handle incoming message from remote
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String incoming = (String)msg.obj;
            Toast.makeText(ClientService.this, incoming, Toast.LENGTH_SHORT).show();
        }
    };
    
    //Handle ping alarms to keep the connection alive
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(mClient == null) {
                return;
            }
            //Ping the MQTT service
            try {
                mClient.ping();
            } catch (MqttException e) {
                e.printStackTrace();
            }
            //Schedule the next alarm
            scheduleKeepAlive();
        }
    };
    
    private void scheduleKeepAlive() {
        long nextWakeup = System.currentTimeMillis() + (KEEP_ALIVE * 1000);
        mManager.set(AlarmManager.RTC_WAKEUP, nextWakeup, alarmIntent);
    }
    
    private void unscheduleKeepAlive() {
        mManager.cancel(alarmIntent);
    }
    
    /* MqttSimpleCallback Methods */
    
    @Override
    public void connectionLost() throws Exception {
        mClient.terminate();
        mClient = null;
        stopSelf();
    }

    @Override
    public void publishArrived(String topicName, byte[] payload, int qos, boolean retained) throws Exception {
        //Be wary of UI related code here!
        //Best to use a Handler for UI or Context operations
        
        StringBuilder builder = new StringBuilder();
        builder.append(topicName);
        builder.append('\n');
        builder.append(new String(payload));
        //Pass the message up to our handler
        Message receipt = Message.obtain(mHandler, 0, builder.toString());
        receipt.sendToTarget();
    }
    
    /*Unused method*/
    //We are not using this service as bound
    //It is explicitly started and stopped with no direct connection
    @Override
    public IBinder onBind(Intent intent) { return null; }
}
