package com.example.tarek.newsfeedapp.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tarek.newsfeedapp.article.Article;
import com.example.tarek.newsfeedapp.R;
import com.example.tarek.newsfeedapp.utils.ArticleQueryUtils;

import java.util.List;

public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    private Context context;
    private final String NO_NAME_AVAILABLE = "author name not available";
    private final String NEW_LINE = "\n";
    private final int ZERO = 0;
    private final int MAX_LENGTH_LINE_1 =11;

    public ArticleArrayAdapter(@NonNull Context context, List<Article> articles) {
        super(context, 0 , articles);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        ViewHolderItem viewHolderItem ;
        if (rootView == null){
            rootView = LayoutInflater.from(context).inflate(R.layout.list_item, parent ,false);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.titleTextView = rootView.findViewById(R.id.title);
            viewHolderItem.sectionTextView = rootView.findViewById(R.id.section);
            viewHolderItem.authorTextView = rootView.findViewById(R.id.author);
            viewHolderItem.dateTextView = rootView.findViewById(R.id.date);

            rootView.setTag(viewHolderItem);
        }else {
            viewHolderItem = (ViewHolderItem) rootView.getTag();
        }
        Article currentArticle = getItem(position);
        viewHolderItem.titleTextView.setText(currentArticle.getTitle());
        viewHolderItem.sectionTextView.setText(currentArticle.getSection());
        viewHolderItem.authorTextView.setText(checkAuthorName(currentArticle.getAuthor()));
        viewHolderItem.dateTextView.setText(makeDateInTwoLines(currentArticle.getDate()));
        return rootView;
    }

    private String makeDateInTwoLines (String inputDate){
        String date  = inputDate.substring(ZERO,MAX_LENGTH_LINE_1); // get first 11 chars to it (Date like 2018-3-13T)
        String time = inputDate.substring(MAX_LENGTH_LINE_1); // get all remained chars
        String formatted = date + NEW_LINE + time ;
        return formatted;
    }

    private String checkAuthorName (String name){
        if (name.equals(ArticleQueryUtils.EMPTY_NAME)) return NO_NAME_AVAILABLE;

        return name;
    }

    private static class ViewHolderItem {
         TextView titleTextView ;
         TextView sectionTextView;
         TextView authorTextView;
         TextView dateTextView;
    }
}