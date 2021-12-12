package com.example.car_crashv3;
import java.io.Serializable;

    public class Score implements Serializable {
        private int coins =0;
        private int distance =0;


        public Score(int coins,int distance){
            this.coins = coins;
            this.distance= distance;
        }

//        public int getScore(int coins,){
//
//        }

        public int getCoins() {
            return coins;
        }

        public int getDistance() {
            return distance;
        }

    }