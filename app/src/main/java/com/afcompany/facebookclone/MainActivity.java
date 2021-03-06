package com.afcompany.facebookclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.top_nav);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.feed));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.request));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.users));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.watch));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.notify));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable. more));

        RecyclerView rv = findViewById(R.id.recucler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter();
        rv.setAdapter(postAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                DividerItemDecoration.VERTICAL);

        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        rv.addItemDecoration(dividerItemDecoration);

        List<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        post1.setImageViewUser(R.drawable.isiah);
        post1.setImageViewPost(R.drawable.pistons);
        post1.setTextViewUsername("Isiah Thomas");
        post1.setTextViewContent("A D??CADA DE 1990 da NBA ?? sin??nimo dos seis t??tulos do Chicago Bulls e do auge de Michael Jordan. E um dos times mais incr??veis da hist??ria da liga teve um advers??rio que incomodou: o Detroit Pistons. E como incomodou. Os Pistons da metade da d??cada de 80 at?? o come??o dos anos 90 se notabilizaram por um outro tipo de jogo, muito diferente dos shows dos Lakers de Magic Johnson e dos Celtics de Bird. F??sica, implac??vel e com uma defesa quase intranspon??vel, a franquia de Michigan n??o tinha a beleza do jogo como marca registrada.");
        post1.setTextViewTitle("www.espn.com.br".toUpperCase());
        post1.setTextViewSubTitle("OS BAD BOYS ENFRENTARAM os Bulls por quatro temporadas consecutivas nos playoffs: na semifinal da confer??ncia de 1988; e nas finais do Leste de 1989, 1990 e 1991.");
        post1.setTextViewTime("1 min");

        Post post2 = new Post();
        post2.setImageViewUser(R.drawable.me);
        post2.setImageViewPost(R.drawable.ja);
        post2.setTextViewUsername("Mateus Ferreira");
        post2.setTextViewContent("?? oficial: Ja Morant foi eleito o melhor calouro da temporada da NBA. Ele foi declarado o vencedor nesta quinta-feira (3) e confirmou o favoritismo.\n" + "\n" +
                "O jogador do Memphis Grizzlies ficou com 99 votos para primeiro lugar, um para segundo e somou 498 pontos nos crit??rios gerais. Em segundo ficou Kendrick Nunn, do Miami Heat, com 56 men????es como o segundo melhor. Zion Williamson, o grande nome do Draft passado, ficou em terceiro.");
        post2.setTextViewTime("2 min");

        posts.add(post1);
        posts.add(post2);

        postAdapter.setPosts(posts);
        postAdapter.notifyDataSetChanged();
    }

    private static class PostViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageViewUser;
        private final ImageView imageViewPost;
        private final TextView textViewTime;
        private final TextView textViewUsername;
        private final TextView textViewContent;
        private final TextView textViewTitle;
        private final TextView textViewSubTitle;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewUser = itemView.findViewById(R.id.img_view_user);
            imageViewPost = itemView.findViewById(R.id.img_view_post);
            textViewTime = itemView.findViewById(R.id.txt_view_time);
            textViewUsername = itemView.findViewById(R.id.txt_view_username);
            textViewContent = itemView.findViewById(R.id.txt_view_content);
            textViewTitle = itemView.findViewById(R.id.txt_view_post_title);
            textViewSubTitle = itemView.findViewById(R.id.txt_view_post_subtitle);
        }

        void bind(Post post){
            imageViewPost.setImageResource(post.getImageViewPost());
            imageViewUser.setImageResource(post.getImageViewUser());
            textViewTime.setText(post.textViewTime);
            textViewUsername.setText(post.textViewUsername);
            textViewContent.setText(post.textViewContent);
            textViewTitle.setText(post.textViewTitle);
            textViewSubTitle.setText(post.textViewSubTitle);

            if (post.getTextViewTitle() == null){
                itemView.findViewById(R.id.post_container).setVisibility(View.GONE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) itemView);
                constraintSet.setDimensionRatio(R.id.img_view_post, "1:1");
                constraintSet.applyTo((ConstraintLayout) itemView);
            } else {
                itemView.findViewById(R.id.post_container).setVisibility(View.VISIBLE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) itemView);
                constraintSet.setDimensionRatio(R.id.img_view_post, "16:9");
                constraintSet.applyTo((ConstraintLayout) itemView);
            }
        }
    }

    private class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

        private List<Post> posts = new ArrayList<>();

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.feed_item, viewGroup, false);
            return new PostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
            Post post = posts.get(i);
            postViewHolder.bind(post);
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public void setPosts(List<Post> posts) {
            this.posts = posts;
        }
    }

    private class Post{

        private int imageViewUser;
        private int imageViewPost;
        private String  textViewTime;
        private String  textViewUsername;
        private String  textViewContent;
        private String  textViewTitle;
        private String  textViewSubTitle;

        public int getImageViewUser() {
            return imageViewUser;
        }

        public void setImageViewUser(int imageViewUser) {
            this.imageViewUser = imageViewUser;
        }

        public int getImageViewPost() {
            return imageViewPost;
        }

        public void setImageViewPost(int imageViewPost) {
            this.imageViewPost = imageViewPost;
        }

        public String getTextViewTime() {
            return textViewTime;
        }

        public void setTextViewTime(String textViewTime) {
            this.textViewTime = textViewTime;
        }

        public String getTextViewUsername() {
            return textViewUsername;
        }

        public void setTextViewUsername(String textViewUsername) {
            this.textViewUsername = textViewUsername;
        }

        public String getTextViewContent() {
            return textViewContent;
        }

        public void setTextViewContent(String textViewContent) {
            this.textViewContent = textViewContent;
        }

        public String getTextViewTitle() {
            return textViewTitle;
        }

        public void setTextViewTitle(String textViewTitle) {
            this.textViewTitle = textViewTitle;
        }

        public String getTextViewSubTitle() {
            return textViewSubTitle;
        }

        public void setTextViewSubTitle(String textViewSubTitle) {
            this.textViewSubTitle = textViewSubTitle;
        }
    }
}