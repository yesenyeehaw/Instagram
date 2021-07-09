//package com.codepath.instagram.Activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.codepath.instagram.Adapters.PostsAdapter;
//import com.codepath.instagram.EndlessRecyclerViewScrollListener;
//import com.codepath.instagram.Models.Post;
//import com.codepath.instagram.R;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseQuery;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FeedActivity extends AppCompatActivity {
//
//    public static final String TAG = "FeedActivity";
//    private SwipeRefreshLayout swipeContainer;
//    private RecyclerView rvPosts;
//    private EndlessRecyclerViewScrollListener scrollListener;
//
//    protected PostsAdapter adapter;
//    protected List<Post> allPosts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_feed);
//        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
//
//        rvPosts = findViewById(R.id.rvPosts);
//
//        allPosts = new ArrayList<>();
//        adapter = new PostsAdapter(this, allPosts);
//
//        // set the adapter on the recycler view
//        rvPosts.setAdapter(adapter);
//        // set the layout manager on the recycler view
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvPosts.setLayoutManager(linearLayoutManager);
//        // query posts from Parstagram
//        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                loadNextDataFromApi(page);
//            }
//        };
//        // Adding the scroll listener to recyclerView
//        rvPosts.addOnScrollListener(scrollListener);
//        queryPosts();
//
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                fetchTimelineAsync();
//            }
//        });
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);
//
//    }
//    public void loadNextDataFromApi(int offset) {
//        // specify what type of data we want to query - Post.class
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        // include data referred by user key
//        query.include(Post.KEY_USER);
//        // limit query to latest 20 items
//        query.setLimit(20);
//        // order posts by creation date (newest first)
//        query.addDescendingOrder("createdAt");
//        // start an asynchronous call for posts
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Toast.makeText(FeedActivity.this, "Issue loading feed...", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // for debugging purposes let's print every post description to logcat
//                for (Post post : posts) {
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//
//                // save received posts to list and notify adapter of new data
//                allPosts.addAll(posts);
//                // Constraints the actual endless scrolling to reshowing the same feed
//                adapter.notifyItemRangeInserted(allPosts.size(), posts.size());
//            }
//        });
//    }
//
//    //Implementing refresh
//    public void fetchTimelineAsync() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.setLimit(20);
//        query.addDescendingOrder("createdAt");
//        query.findInBackground(
//        new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                // check for errors
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting refreshed posts", e);
//                    return;
//                }
//
//                // Clear out old items before appending in the new ones
//                adapter.clear();
//                // The data has come back, add new items to adapter
//                adapter.addAll(posts);
//                // Call setRefreshing(false) to signal refresh has finished
//                swipeContainer.setRefreshing(false);
//            }
//        });
//    }
//    private void queryPosts() {
//        // specify what type of data we want to query - Post.class
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        // include data referred by user key
//        query.include(Post.KEY_USER);
//        // limit query to latest 20 items
//        query.setLimit(20);
//        // order posts by creation date (newest first)
//        query.addDescendingOrder("createdAt");
//        // start an asynchronous call for posts
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Toast.makeText(FeedActivity.this, "Issue loading feed...", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // for debugging purposes let's print every post description to logcat
//                for (Post post : posts) {
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//
//                // save received posts to list and notify adapter of new data
//                allPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
//}
