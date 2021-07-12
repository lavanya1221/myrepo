package com.example.assignment2;

import android.app.Application;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MemberRepository {

    private MemberDao MemberDao;
    private LiveData<List<Member>> allMembers;


    public MemberRepository(Application application){
        MemberDatabase database =MemberDatabase.getInstance(application);
        MemberDao = database.MemberDao();
        allMembers=MemberDao.getAllMember();
    }

    public void insert(Member member){
        new InsertMemberAsyncTask(MemberDao).execute(member);
    }

    public void delete(Member member){
        new DeleteMemberAsyncTask(MemberDao).execute(member);
    }

    public LiveData<List<Member>>getAllMembers(){
        return allMembers;
    }
    private static class InsertMemberAsyncTask extends AsyncTask<Member, Void, Void> {
        private MemberDao memberDao;
        private InsertMemberAsyncTask(MemberDao memberDao){
            this.memberDao=memberDao;
        }

        protected Void doInBackground(Member... members){
            memberDao.insert(members[0]);
            return null;
        }
    }
    private static class DeleteMemberAsyncTask extends AsyncTask<Member, Void, Void> {
        private MemberDao memberDao;
        private DeleteMemberAsyncTask(MemberDao memberDao){
            this.memberDao=memberDao;
        }

        protected Void doInBackground(Member... members){
            memberDao.delete(members[0]);
            return null;
        }
    }
}


