package dto;

import java.time.LocalDate;

public class Talk {

public class StoreMemo {
private int topic_id;
private String topic_name;
// コンストラクタ
public StoreMemo(int storeId, LocalDate messageDate, String note) {
this.topic_id = topic_id;
this.topic_name = topic_name;
}
// ゲッター／セッター
public int getTopic_id() {
return topic_id;
}

public void setTopic_id(int topic_id) {
this.topic_id = topic_id;
}

public String getTopic_name() {
return topic_name;
}

public void setTopic_name(String topic_name) {
this.topic_name = topic_name;
}
}
}
