package com.example.petteri.nuorisotapahtuma;

abstract class User {
    protected String name;

    class QuestUser extends User {
        public QuestUser() {
            name = "Quest";
        }
    }

    class SuperUser extends User {
        public SuperUser() {
            name = "Super";
        }
        //@Override
        //Metodit: protected void asd()
    }

    class AdminUser extends User {
        public AdminUser() {
            name = "Admin";
        }
        //@Override
        //Metodit: protected void asd()
    }
}
