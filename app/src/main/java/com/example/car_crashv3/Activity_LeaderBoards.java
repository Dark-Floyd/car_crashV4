package com.example.car_crashv3;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;



    public class Activity_LeaderBoards extends Fragment {

        public static final int LEADERBOARDS_SIZE = 10;
        private TextView scores[];
        private TextView odometers[];
        private TableRow tableRows[];
        private TextView names[];


        private String stringName[] = new String[LEADERBOARDS_SIZE];



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_leaderboards,
                    container,false);
            findViews(view);
            //initViews();
            // setScores(MyDB.getDB().getRecords());


            return view;
        }

        private void setScores(ArrayList<Score> scores) {
            for(int i=0; i<LEADERBOARDS_SIZE;i++){


                  //  Score score = scores.get(i);




                }
            }






        private void findViews(View view) {
            tableRows = new TableRow[]{
                    view.findViewById(R.id.leaderboards_score_row1),
                    view.findViewById(R.id.leaderboards_score_row2),
                    view.findViewById(R.id.leaderboards_score_row3),
                    view.findViewById(R.id.leaderboards_score_row4),
                    view.findViewById(R.id.leaderboards_score_row5),
                    view.findViewById(R.id.leaderboards_score_row6),
                    view.findViewById(R.id.leaderboards_score_row7),
                    view.findViewById(R.id.leaderboards_score_row8),
                    view.findViewById(R.id.leaderboards_score_row9),
                    view.findViewById(R.id.leaderboards_score_row10)
            };

            names = new TextView[]{
                    view.findViewById(R.id.leaderboards_score_name1),
                    view.findViewById(R.id.leaderboards_score_name2),
                    view.findViewById(R.id.leaderboards_score_name3),
                    view.findViewById(R.id.leaderboards_score_name4),
                    view.findViewById(R.id.leaderboards_score_name5),
                    view.findViewById(R.id.leaderboards_score_name6),
                    view.findViewById(R.id.leaderboards_score_name7),
                    view.findViewById(R.id.leaderboards_score_name8),
                    view.findViewById(R.id.leaderboards_score_name9),
                    view.findViewById(R.id.leaderboards_score_name10)

            };
            scores = new TextView[]{
                    view.findViewById(R.id.leaderboards_score_pts1),
                    view.findViewById(R.id.leaderboards_score_pts2),
                    view.findViewById(R.id.leaderboards_score_pts3),
                    view.findViewById(R.id.leaderboards_score_pts4),
                    view.findViewById(R.id.leaderboards_score_pts5),
                    view.findViewById(R.id.leaderboards_score_pts6),
                    view.findViewById(R.id.leaderboards_score_pts7),
                    view.findViewById(R.id.leaderboards_score_pts8),
                    view.findViewById(R.id.leaderboards_score_pts9),
                    view.findViewById(R.id.leaderboards_score_pts10)

            };
            odometers = new TextView[]{
                    view.findViewById(R.id.leaderboards_odo1),
                    view.findViewById(R.id.leaderboards_odo2),
                    view.findViewById(R.id.leaderboards_odo3),
                    view.findViewById(R.id.leaderboards_odo4),
                    view.findViewById(R.id.leaderboards_odo5),
                    view.findViewById(R.id.leaderboards_odo6),
                    view.findViewById(R.id.leaderboards_odo7),
                    view.findViewById(R.id.leaderboards_odo8),
                    view.findViewById(R.id.leaderboards_odo9),
                    view.findViewById(R.id.leaderboards_odo10)

            };

        }
}
