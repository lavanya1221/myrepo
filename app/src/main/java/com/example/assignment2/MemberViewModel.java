package com.example.assignment2;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MemberViewModel extends AndroidViewModel {

    private MemberRepository repository;
    private LiveData<List<Member>> allMembers;

    public MemberViewModel( Application application) {
        super(application);
        repository = new MemberRepository(application);
        allMembers=repository.getAllMembers();
    }

    public void insert(Member member){
        repository.insert(member);
    }
    public void delete(Member member){
        repository.delete(member);
    }
    public LiveData<List<Member>>getAllMembers(){
        return allMembers;
    }
}
