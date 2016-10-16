#include <android/log.h>
#include <android_native_app_glue.h>

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, \
                                             "hlnademo", \
                                             __VA_ARGS__))
 
static void handle_cmd(struct android_app* app, int32_t cmd)
{
   switch (cmd)
   {
      case APP_CMD_SAVE_STATE:
           LOGI("Save state");
           break;

      case APP_CMD_INIT_WINDOW:
           LOGI("Init window");
           break;

      case APP_CMD_TERM_WINDOW:
           LOGI("Terminate window");
           break;

      case APP_CMD_PAUSE:
           LOGI("Pausing");
           break;

      case APP_CMD_RESUME:
           LOGI("Resuming");
           break;

      case APP_CMD_STOP:
           LOGI("Stopping");
           break;

      case APP_CMD_DESTROY:
           LOGI("Destroying");
           break;

      case APP_CMD_LOST_FOCUS:
           LOGI("Lost focus");
           break;

      case APP_CMD_GAINED_FOCUS:
           LOGI("Gained focus");
   }
}

static int32_t handle_input(struct android_app* app, AInputEvent* event)
{
   if (AInputEvent_getType(event) == AINPUT_EVENT_TYPE_MOTION)
   {
      size_t pointerCount = AMotionEvent_getPointerCount(event);
      size_t i;
      for (i = 0; i < pointerCount; ++i)
      {
         LOGI("Received motion event from %zu: (%.2f, %.2f)", i, 
              AMotionEvent_getX(event, i), AMotionEvent_getY(event, i));
      }
      return 1;
   }
   else if (AInputEvent_getType(event) == AINPUT_EVENT_TYPE_KEY)
   {
      LOGI("Received key event: %d", AKeyEvent_getKeyCode(event));
      if (AKeyEvent_getKeyCode(event) == AKEYCODE_BACK)
         ANativeActivity_finish(app->activity);
      return 1;
   }
   return 0;
}

void android_main(struct android_app* state)
{
   app_dummy(); // prevent glue from being stripped
 
   state->onAppCmd = &handle_cmd;
   state->onInputEvent = &handle_input;

   while(1)
   {
      int ident;
      int fdesc;
      int events;
      struct android_poll_source* source;
 
      while ((ident = ALooper_pollAll(0, &fdesc, &events, (void**)&source)) >= 0)
      {
         if (source)
            source->process(state, source);

         if (state->destroyRequested)
            return;
      }
   }
}