LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := llnademo
LOCAL_SRC_FILES := llnademo.c
LOCAL_LDLIBS := -llog -landroid
include $(BUILD_SHARED_LIBRARY)