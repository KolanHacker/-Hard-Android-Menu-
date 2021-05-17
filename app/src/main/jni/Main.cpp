#include <list>
#include <vector>
#include <string.h>
#include <GLES2/gl2.h>
#include <pthread.h>
#include <cstring>
#include <jni.h>
#include <unistd.h>
#include <fstream>
#include "Includes/obfuscate.h"
#include "KittyMemory/MemoryPatch.h"
#include "Includes/Logger.h"
#include "Includes/Utils.h"
#include "Menu.h"
#include "Toast.h"
#include <And64InlineHook/And64InlineHook.hpp>
#include <chrono>
#include "IL2CppResolver/SigUtils.h"
#include "IL2CppResolver/Resolver.h"
#include <pthread.h>
#include <jni.h>
#include <GLES2/gl2.h>
#include <dlfcn.h>
#include "KittyMemory/MemoryPatch.h"
#include "Includes/Logger.h"
#include "Unity/Unity.h"
#include "Includes/Utils.h"
#include <pthread.h>
#include <jni.h>
#include "KittyMemory/MemoryPatch.h"
#include "Includes/Logger.h"
#include <GLES2/gl2.h>
#include <dlfcn.h>
#include "KittyMemory/MemoryPatch.h"
#include "Includes/Logger.h"
#include "Includes/Utils.h"
#include "GameData/GameData.h"
#include "GameData/Offsets.h"
#include "Unity/Vector2.h"
#include "Unity/Rect.h"
#include "Unity/Color.h"
#include "Unity/Quaternion.h"
#include <sstream>
#include <codecvt>


#if defined(__aarch64__)
//#include <And64InlineHook/And64InlineHook.hpp>
#else

#include <Substrate/SubstrateHook.h>
#include <Substrate/CydiaSubstrate.h>

#endif


// fancy struct for patches for kittyMemory
struct My_Patches {
     MemoryPatch procodeleecher,bouzeleecher,voidclown;
} hexPatches;



#define targetLibName OBFUSCATE("libil2cpp.so")

extern "C" {
JNIEXPORT jobjectArray
JNICALL
Java_com_simplefucker_source_ModMenu_getSettings(JNIEnv *env, jobject activityObject) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("0_Category_First Color"),
            OBFUSCATE("-110_InputValue_First Color Red"),
            OBFUSCATE("-112_InputValue_First Color Green"),
            OBFUSCATE("-111_InputValue_First Color Blue"),
            OBFUSCATE("0_Category_Secondary Color"),
            OBFUSCATE("-210_InputValue_First Color Red"),
            OBFUSCATE("-212_InputValue_First Color Green"),
            OBFUSCATE("-211_InputValue_First Color Blue"),
    };

    int Total_Feature = (sizeof features /
                         sizeof features[0]); //Now you dont have to manually update the number everytime;
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));
    int i;
    for (i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    return (ret);
}


JNIEXPORT jobjectArray
JNICALL
Java_com_simplefucker_source_ModMenu_getPlayer(JNIEnv *env, jobject activityObject) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("0_Category_Player Category"),
            OBFUSCATE("0_ButtonOnOff_Click Me"),
            OBFUSCATE("0_SeekBar_Preview SeekBar_1_13131"),
    };

    int Total_Feature = (sizeof features /
                         sizeof features[0]); //Now you dont have to manually update the number everytime;
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));
    int i;
    for (i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    pthread_t ptid;
    pthread_create(&ptid, NULL, antiLeech, NULL);

    return (ret);
}
JNIEXPORT jobjectArray
JNICALL
Java_com_simplefucker_source_ModMenu_getVisuals(JNIEnv *env, jobject activityObject) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("0_Category_Esp"),
            OBFUSCATE("0_ButtonOnOff_Click Me"),
            OBFUSCATE("0_SeekBar_Preview SeekBar_1_13131"),
    };

    int Total_Feature = (sizeof features /
                         sizeof features[0]); //Now you dont have to manually update the number everytime;
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));
    int i;
    for (i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    pthread_t ptid;
    pthread_create(&ptid, NULL, antiLeech, NULL);

    return (ret);
}
JNIEXPORT jobjectArray
JNICALL
Java_com_simplefucker_source_ModMenu_getWeapon(JNIEnv *env, jobject activityObject) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("0_Category_Your Category"),
            OBFUSCATE("0_ButtonOnOff_Click Me"),
            OBFUSCATE("0_SeekBar_Preview SeekBar_1_13131"),
    };

    int Total_Feature = (sizeof features /
                         sizeof features[0]); //Now you dont have to manually update the number everytime;
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));
    int i;
    for (i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    pthread_t ptid;
    pthread_create(&ptid, NULL, antiLeech, NULL);

    return (ret);
}
JNIEXPORT jobjectArray
JNICALL
Java_com_simplefucker_source_ModMenu_getMisc(JNIEnv *env, jobject activityObject) {
    jobjectArray ret;

    const char *features[] = {
            OBFUSCATE("0_Category_The Preview:D"),
            OBFUSCATE("0_SeekBar_Preview SeekBar_1_4"),
            OBFUSCATE("0_ButtonOnOff_Click Me"),
    };

    int Total_Feature = (sizeof features /
                         sizeof features[0]); //Now you dont have to manually update the number everytime;
    ret = (jobjectArray)
            env->NewObjectArray(Total_Feature, env->FindClass(OBFUSCATE("java/lang/String")),
                                env->NewStringUTF(""));
    int i;
    for (i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));

    pthread_t ptid;
    pthread_create(&ptid, NULL, antiLeech, NULL);

    return (ret);
}
JNIEXPORT void JNICALL
Java_com_simplefucker_source_SavedPrefs_Changes(JNIEnv *env, jclass clazz, jobject obj,
                                                jint feature, jint value, jboolean boolean, jstring str) {

    const char *featureName = env->GetStringUTFChars(str, 0);

    LOGD(OBFUSCATE("Feature name: %d - %s | Value: = %d | Bool: = %d"), feature, featureName, value,
         boolean);

    //!!! BE CAREFUL NOT TO ACCIDENTLY REMOVE break; !!!//

    switch (feature) {
        case 1:
        break;
    }
}
}

void *IoioioO(void *) {

#if defined(__aarch64__)

#else

#endif

    return NULL;
}

__attribute__((constructor))
void lib_main() {
    pthread_t ptid;
    pthread_create(&ptid, NULL, IoioioO, NULL);
}

