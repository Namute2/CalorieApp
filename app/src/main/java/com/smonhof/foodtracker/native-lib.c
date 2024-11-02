#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <android/log.h>

#define LOG_TAG "NativeLib"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

void nativeAction(float value) {
    LOGI("New amount: %f", value);
}

JNIEXPORT void JNICALL
Java_com_smonhof_foodtracker_fragments_CustomMealFragment_onTextChangedJNI(
        JNIEnv *env, jobject thiz, jstring text) {
const char *nativeText = (*env)->GetStringUTFChars(env, text, 0);

if (nativeText != NULL) {
char *end;
float newAmount = strtof(nativeText, &end);
if (*end == '\0') {
nativeAction(newAmount);
}
(*env)->ReleaseStringUTFChars(env, text, nativeText);
}
}