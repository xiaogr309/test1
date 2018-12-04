package test.model;

import java.io.Serializable;

public class UsMo implements Serializable{
    private Long id;
    private Long activity_id;
    public static final String ID = "id";
    public static final String ACTIVITY_ID = "activity_id";
    public UsMo(){

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getActivity_id(){
        return activity_id;
    }

    public void setActivity_id(Long activity_id){
        this.activity_id = activity_id;
    }

}
