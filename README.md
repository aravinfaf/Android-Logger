# Android-Logger

  //Logger Dependencies in build.gradle(Module:app)
  
    implementation 'org.slf4j:slf4j-api:1.7.25'
    implementation 'com.github.tony19:logback-android:2.0.0'
    
   # Add logback.xml in android assets
      Add logback.xml in ----> project/app/src/main/assets/logback.xml
      
   #logback.xml
   
   <configuration debug="true">

    <!-- this is the app local data area so that we do not need storage permission to write there -->
    <property name="LOG_HOME" value="${DATA_DIR}" />

    <!-- Create a logcat appender -->
    <appender name="logcat" class="ch.qos.logback.classic.android.LogcatAppender">
        <encoder>
            <pattern>%msg</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_HOME}/app.txt</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
            <fileNamePattern>${LOG_HOME}/app.%i.txt</fileNamePattern>
            <minIndex>1</minIndex>
            <maxIndex>2</maxIndex>
        </rollingPolicy>

        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <maxFileSize>500KB</maxFileSize>
        </triggeringPolicy>
        <encoder>
            <pattern>%date{yyyy-MMM-dd HH:mm:ss.SSS} %t %r %logger{15} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="main" level="DEBUG">
        <appender-ref ref="logcat" />
        <appender-ref ref="FILE" />
    </logger>

    <root level="DEBUG">
        <appender-ref ref="logcat" />
        <appender-ref ref="FILE" />
    </root>

</configuration>

# Configuration in Java/Kotlin class
    val logger = LoggerFactory.getLogger(Activity::class.java)
    
    Inside onCreate() and onViewCreated()
        
        logger.debug("Debug")
        logger.warn("Warn")
        logger.info("Info")
        logger.error("Error")

# File storage location(File Appender)
    data/data/PACKAGE_NAME/files/app.txt

# Runtime permission
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="28" />
    <uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
