package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.api.APIInterface;
import com.example.myapplication.entity.News;
import com.example.myapplication.entity.NewsFeedData;
import com.example.myapplication.network.APIClient;
import com.example.myapplication.network.NewsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewPagerItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewPagerItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPagerItemFragment extends Fragment implements ItemClickListener<News> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String CATEGORY = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private APIInterface apiInterface;
    private ProgressBar progressbar;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int pageNo = 0;
    private boolean isLoading = false;
    private int pageSize = 10;

    public ViewPagerItemFragment() {
    }


    public static ViewPagerItemFragment newInstance(String param1, String param2) {
        ViewPagerItemFragment fragment = new ViewPagerItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(CATEGORY, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(CATEGORY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        progressbar = (ProgressBar) view.findViewById(R.id.progress_bar);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        progressbar.setVisibility(View.GONE);
        initAdapter();
        initScrollListener();
        return view;
    }

    List<News> itemList = new ArrayList<>();

    private void initAdapter() {

        itemList.add(null);
        recyclerViewAdapter = new RecyclerViewAdapter(itemList, getActivity(),this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == itemList.size() - 1) {
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });


    }

    private void loadMore() {
        if (itemList.size() != 1) {
            itemList.add(null);
            recyclerViewAdapter.notifyItemInserted(itemList.size() - 1);
        }
        hitAPi(++pageNo, pageSize);
    }

    private void doLoading(NewsFeedData news) {
        if (itemList.size() > 0) {
            itemList.remove(itemList.size() - 1);
        }
        int scrollPosition = itemList.size();
        recyclerViewAdapter.notifyItemRemoved(scrollPosition);
        itemList.addAll(news.getContent());
        recyclerViewAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    private void hitAPi(int pageNo, int pageSize) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<NewsFeedData> call = apiInterface.getNews(pageNo, pageSize, "lastUpdated", mParam2);

        call.enqueue(new Callback<NewsFeedData>() {
            @Override
            public void onResponse(Call<NewsFeedData> call, Response<NewsFeedData> response) {
                progressbar.setVisibility(View.GONE);
                NewsFeedData news = response.body();
                doLoading(news);
            }

            @Override
            public void onFailure(Call<NewsFeedData> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(News news) {

        Intent intent = new Intent(getActivity(),NewsDetailsActivity.class);
        intent.putExtra(NewsDetailsActivity.NEWS_ITEM,news);
        startActivity(intent);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
