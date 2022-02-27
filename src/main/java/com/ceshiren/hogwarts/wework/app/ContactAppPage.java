package com.ceshiren.hogwarts.wework.app;

public class ContactAppPage {
    public MemberAppPage toMember(){
        return new MemberAppPage();
    }
    public MemberAppPage searchMember(String pattern){
        return new MemberAppPage();
    }
    public ContactAppPage addMember(UserProfile user){
        //todo:
        return this;
    }
}
