#include <jni.h>
 
jstring
   Java_ca_tutortutor_ndkgreetings_NDKGreetings_getGreetingMessage(JNIEnv* env,
                                                                   jobject this)
{
   return (*env)->NewStringUTF(env, "Greetings from the NDK!");
}