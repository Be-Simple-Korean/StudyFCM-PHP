# StudyFCM-PHP
## 과정
* 1. 의존성 추가
  ```implementation 'com.squareup.okhttp3:okhttp:3.14.6'
    implementation 'com.google.firebase:firebase-messaging:10.2.1'
  ```
* 2. Firebase에 앱 등록과정 수행
* 3. FirebaseInstanceIDService와 FirebaseMessagingService를 상속받는 클래스 생성
* 4. 생성한 각 클래스에 onTokenRefresh()와 onMessageReiceved() 오버라이딩
* 5. 
  * 5-1. manifest.xml에서 인터넷 퍼미션 추가
  * 5-2. <application> 태그안에 useCleartextTraffic=true 지정
  * 5-3. <application> 태그안에 <service> 태그 추가
    ```
        <service android:name=".FirebaseMessagingService">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>
        <service android:name=".MyFirebaseInstanceIDService">
            <intent-filter>
                <action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
            </intent-filter>
        </service>
    ```
* 6. FCM을 시작할 부분에 코드 작성
    '''
    FirebaseInstanceId.getInstance().getToken(); = 토큰값을 얻는 코드
    FirebaseMessaging.getInstance().subscribeToTopic("test message"); = fcm 주제 지정
    '''
* 7. okHttp를 이용하여 php파일에 토큰값을 넘겨주기
* 8. 파이어베이스 - 해당 앱 - 설정 - 클라우드 메시징 서버키를 복사해서 
     php 파일 내
     ```
     $headers = array(
			'Authorization:key = 서버키 붙여넣기','Content-Type: application/json'
			);
     ```
     부분에 서버 키 붙여넣기
     ※ 토큰값은 Array형태로 넘겨줘야함
* 위의 절차를 마치면 파이어베이스에서 FCM전송이 가능하고 onMessageReiceved()메소드에서 
  커스텀 Notification등 메시지 처리가 가능함.
