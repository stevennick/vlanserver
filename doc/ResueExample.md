#  Save servlet memory usage by reuse large object

在建構Servlet時，常常會需要使用到某些服務或程式來協助Servlet對外提供服務。有時候，這類服務會需要很多時間初始化（如database pool、web service client等），或者它所提供的服務是可以共享的。對於這類服務，若等到每次需要存取時才將它們初始化，除了花費許多系統時間來建立物件外，也無形中浪費許多記憶體空間。比較好的方式，是將這類服務設計為可以共享資源的架構，並在Servlet啟動階段就進行初始化動作，這樣一來，當需要進行存取時，服務就能略過初始化與記憶體分配的工作，達到快速存取服務並節省記憶體的目的。

具體的作法，是使用Singleton pattern，讓類別由Factory中產生實例，取代原先在存取服務的地方初始化新的物件。請參考ProcessWorker.java，即為Singleton pattern的實作範例。

![ProcessWorker.java](http://i.imgur.com/QKmFcpp.png)

為了達到這樣的效果，通常會把初始化該類別的程式碼寫在類別本身，並且定義一靜態物件參考指向自己，同時宣告初始化物件的程式碼為私有函式，避免被外界意外初始化。而在多執行緒環境下，為了避免競爭而產生多個物件，需要對初始化的程式區段加上同步區限制，確保只有一個物件會被初始化。一旦物件被初始化完成，產生的實例將指派至物件參考中，之後不管外界如何取得物件，將永遠取得同一物件實例。欲呼叫ProcessWorker的程式段，只要呼叫ProcessWorker.getInstance()，就可以取得ProcessWorker物件，而第一次呼叫ProcessWorker.getInstance()時，系統才會分配記憶體並初始化物件，從而達到節省記憶體的效果。

若初始化工作會花費很長的時間，則可透過指定Servlet啟動順序，來指定Servlet在啟動時期就先初始化物件實例。其中一個例子是撰寫Starter servlet class，裡面依序進行整個Servlet的物件初始化工作，然後透過編輯web.xml，指定該servlet先行啟動。這樣一來，就可以保證所有物件在真正的服務開始提供前就已經就緒，從而縮短第一個服務請求所花費之時間。

在範例程式中，可以看到Starter在Servlet startup時就被啟動，並且在console下輸出ProcessWorker的Object ID，稍後Jersey Web Application被啟動，系統上線後，外部可以透過存取網址 /rest/vlan/xxx/demo ，引發VlanService初始化並取得ProcessWorker實例。透過比對輸出於回應中的Object ID，可以知道VlanService所取得的ProcessWorker實例即為Starter所初始化完成的實例。

![透過觀察得知取得同一ProcessWorker物件](http://i.imgur.com/upgzWJM.png)

在實務上，通常會將Database Connections Pool、RESTFul Web Service Client、System Configuration等這類基礎服務設計為可在單一物件週期中重複使用的架構，並且在Servlet啟動時，尚未對外提供服務前就進行初始化工作。設計此類架構時，需要特別注意避免變數污染與使用可變動的全域變數儲存使用者資訊，同時也要注意當例外發生時（例如資料庫連線短暫中斷）的相關例外處理以及處在不穩定的環境時（例如網路中斷導致WebService斷線）下的自我恢復能力。最後，根據系統設計要求的可靠性等級，決定該採用何種方式恢復服務。若能確實分割使用者資料，這類可重用的設計架構將可以顯著減少記憶體用量，同時獲得服務快速反應的額外好處。

本範例完整的程式碼放在GitHub上，下載後匯入Eclipse EE就可以執行。
若有興趣歡迎來信討論。
https://github.com/stevennick/vlanserver
