#include <android/log.h>
#include <android/native_activity.h>
#include <pthread.h>

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, \
                                             "llnademo", \
                                             __VA_ARGS__))

AInputQueue* _queue;
pthread_t thread;
pthread_cond_t cond;
pthread_mutex_t mutex;

static void onConfigurationChanged(ANativeActivity* activity) 
{
   LOGI("ConfigurationChanged: %p\n", activity);
}

static void onDestroy(ANativeActivity* activity) 
{
   LOGI("Destroy: %p\n", activity);
}

static void onInputQueueCreated(ANativeActivity* activity, AInputQueue* queue)
{
   LOGI("InputQueueCreated: %p -- %p\n", activity, queue);
   pthread_mutex_lock(&mutex);
   _queue = queue;
   pthread_cond_broadcast(&cond);
   pthread_mutex_unlock(&mutex);
}

static void onInputQueueDestroyed(ANativeActivity* activity, AInputQueue* queue)
{
   LOGI("InputQueueDestroyed: %p -- %p\n", activity, queue);
   pthread_mutex_lock(&mutex);
   _queue = NULL;
   pthread_mutex_unlock(&mutex);
}

static void onLowMemory(ANativeActivity* activity) 
{
   LOGI("LowMemory: %p\n", activity);
}

static void onNativeWindowCreated(ANativeActivity* activity, 
                                  ANativeWindow* window) 
{
   LOGI("NativeWindowCreated: %p -- %p\n", activity, window);
}

static void onNativeWindowDestroyed(ANativeActivity* activity, 
                                    ANativeWindow* window) 
{
   LOGI("NativeWindowDestroyed: %p -- %p\n", activity, window);
}

static void onPause(ANativeActivity* activity) 
{
   LOGI("Pause: %p\n", activity);
}

static void onResume(ANativeActivity* activity) 
{
   LOGI("Resume: %p\n", activity);
}

static void* onSaveInstanceState(ANativeActivity* activity, size_t* outLen) 
{
   LOGI("SaveInstanceState: %p\n", activity);
   return NULL;
}

static void onStart(ANativeActivity* activity) 
{
   LOGI("Start: %p\n", activity);
}

static void onStop(ANativeActivity* activity) 
{
   LOGI("Stop: %p\n", activity);
}

static void onWindowFocusChanged(ANativeActivity* activity, int focused) 
{
   LOGI("WindowFocusChanged: %p -- %d\n", activity, focused);
}

static void* process_input(void* param) 
{
   while (1)
   {
      pthread_mutex_lock(&mutex);
      if (_queue == NULL)
         pthread_cond_wait(&cond, &mutex);
      AInputEvent* event = NULL;
      while (AInputQueue_getEvent(_queue, &event) >= 0)
      {
         if (AInputQueue_preDispatchEvent(_queue, event))
            break;
         AInputQueue_finishEvent(_queue, event, 0);
      }
      pthread_mutex_unlock(&mutex);
   }
}

void ANativeActivity_onCreate(ANativeActivity* activity,
                              void* savedState, 
                              size_t savedStateSize) 
{
   LOGI("Creating: %p\n", activity);
   LOGI("Internal data path: %s\n", activity->internalDataPath);
   LOGI("External data path: %s\n", activity->externalDataPath);
   LOGI("SDK version code: %d\n", activity->sdkVersion);
   LOGI("Asset Manager: %p\n", activity->assetManager);

   activity->callbacks->onConfigurationChanged = onConfigurationChanged;
   activity->callbacks->onDestroy = onDestroy;
   activity->callbacks->onInputQueueCreated = onInputQueueCreated;
   activity->callbacks->onInputQueueDestroyed = onInputQueueDestroyed;
   activity->callbacks->onLowMemory = onLowMemory;
   activity->callbacks->onNativeWindowCreated = onNativeWindowCreated;
   activity->callbacks->onNativeWindowDestroyed = onNativeWindowDestroyed;
   activity->callbacks->onPause = onPause;
   activity->callbacks->onResume = onResume;
   activity->callbacks->onSaveInstanceState = onSaveInstanceState;
   activity->callbacks->onStart = onStart;
   activity->callbacks->onStop = onStop;
   activity->callbacks->onWindowFocusChanged = onWindowFocusChanged;

   pthread_mutex_init(&mutex, NULL);
   pthread_cond_init(&cond, NULL);
   pthread_create(&thread, NULL, process_input, NULL);
}