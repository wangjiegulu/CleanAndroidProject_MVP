package com.wangjiegulu.cleanandroidprojectmvp.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wangjiegulu.cleanandroidprojectmvp.R;
import com.wangjiegulu.cleanandroidprojectmvp.ui.base.BaseActivity;
import com.wangjiegulu.cleanandroidprojectmvp.ui.main.adapter.RepositoryAdapter;
import com.wangjiegulu.cleanandroidprojectmvp.ui.main.vm.MainRepositoryVM;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.IMainViewer {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RepositoryAdapter adapter;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewerComponent().inject(this);
        presenter.bind(this);

        RecyclerView repositoryRv = findViewById(R.id.activity_main_rv);

        repositoryRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RepositoryAdapter();
        adapter.setOnRepositoryAdapterItemClickListener(this);
        repositoryRv.setAdapter(adapter);

        presenter.requestUserRepositories("wangjiegulu");

    }

    @Override
    public void onRequestUserRepositories(List<MainRepositoryVM> githubRepositories) {
        adapter.setList(githubRepositories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRepositoryAdapterItemClick(MainRepositoryVM mainRepositoryVM, int position) {
        showToast(mainRepositoryVM.getModel().getName() + " clicked");
    }

}
