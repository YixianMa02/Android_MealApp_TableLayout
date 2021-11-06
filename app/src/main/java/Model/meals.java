package Model;

import android.graphics.Color;

import androidx.annotation.NonNull;

public class meals {
    private int id;
    private String description;
    //private int textColor, backColor;

    public meals(int id, String description) {
        this.id = id;
        this.description = description;
        //this.textColor = Color.BLACK;
        //this.backColor = 0;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @NonNull
    @Override
    public String toString() { return description; }
}
