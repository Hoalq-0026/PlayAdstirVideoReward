
# adstir
-dontwarn com.ad_stir.**
-keep class com.ad_stir.** { *; }
-keep interface com.ad_stir.** { *; }
-keepattributes EnclosingMethod

# google
-keep class com.google.android.gms.ads.** { *; }
-keep class android.support.customtabs.** { *; }

# adcolony
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-ignorewarnings

# applovin
-dontwarn com.applovin.**
-keep class com.applovin.**.*

# unity-ads
-keepattributes SourceFile,LineNumberTable
-keepattributes JavascriptInterface
-keep class android.webkit.JavascriptInterface {
   *;
}
-keep class com.unity3d.ads.** {
   *;
}

# maio
-dontwarn jp.maio.**
-keep class jp.maio.** { *; }
-keep interface jp.maio.** { *; }

# vungle
-dontwarn com.vungle.**
-keep class com.vungle.** { *; }
-keep class javax.inject.*
-keepattributes *Annotation*
-keepattributes Signature
-keep class dagger.*
# GreenRobot
-dontwarn de.greenrobot.event.util.**
# RxJava
-dontwarn rx.internal.util.unsafe.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
   rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
   rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-keep class rx.schedulers.Schedulers { public static <methods>; }
-keep class rx.schedulers.ImmediateScheduler { public <methods>; }
-keep class rx.schedulers.TestScheduler { public <methods>; }
-keep class rx.schedulers.Schedulers { public static ** test(); }

# MOAT
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }

# Retrofit
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8

# tapjoy
-keep class com.tapjoy.** { *; }
-keepattributes JavascriptInterface
-keep class * extends java.util.ListResourceBundle {
protected Object[][] getContents();
}
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
public static final *** NULL;
}
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
@com.google.android.gms.common.annotation.KeepName *;
}
-keepnames class * implements android.os.Parcelable {
public static final ** CREATOR;
}
-keep class com.google.android.gms.ads.identifier.** { *; }
-dontwarn com.tapjoy.internal.**

# mopub
-keep class com.mopub.** { *; }
-dontwarn com.mopub.**


# imobile
-keep class jp.co.imobile.** {*;}
-dontwarn jp.co.imobile.**

# careward
-keepattributes Exceptions,InnerClasses, *Annotation*, Signature, JavascriptInterface, EnclosingMethod
-dontwarn jp.co.mediasdk.**

# Mobvista
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.mobvista.** {*; }
-keep interface com.mobvista.** {*; }
-keep class android.support.v4.** { *; }
-dontwarn com.mobvista.**
-keep class **.R$* { public static final int mobvista*; }
-keep class com.alphab.** {*; }
-keep interface com.alphab.** {*; }
-keep class jp.co.mediasdk.** { *; }

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

##### 導入するアプリのパッケージ名を設定してください。
-keeppackagenames com.digital.playadstirvideoreward
-keepdirectories com/digital/playadstirvideoreward
-keep class com.digital.playadstirvideoreward.R
