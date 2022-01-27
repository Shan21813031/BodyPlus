package com.apress.gerber.bodyplus;

public class User{

    public String fullName, age, email, weight, height, dl, squat, bp, weightGoal, bpGoal, dlGoal,
            fTri, sTri, fBi, sBi, fSh, sSh, tSh, fLegs, sLegs, tLegs, foLegs, fiLegs,
            siLegs, fChest, sChest, fAbs, sAbs, tAbs, fBack, sBack, squatGoal;

    public User(){

    }

    public User(String fullName, String age, String email, String weight, String height,
                String dl, String squat, String bp, String weightGoal, String bpGoal, String dlGoal,
                String squatGoal, String fTri, String sTri, String fBi, String sBi, String fSh,
                String sSh, String tSh, String fLegs, String sLegs, String tLegs, String foLegs,
                String fiLegs, String siLegs, String fChest, String sChest, String fAbs,
                String sAbs, String tAbs, String fBack, String sBack){

        this.fullName = fullName;
        this.age = age;
        this.email = email;

        // statistics below

        this.weight = weight;
        this.height = height;

        this.dl = dl;
        this.squat = squat;
        this.bp = bp;

        this.dlGoal = dlGoal;
        this.bpGoal = bpGoal;
        this.squatGoal = squatGoal;
        this.weightGoal = weightGoal;

        // exercise choosing below

        //arms

        this.fTri = fTri;
        this.sTri = sTri;
        this.fBi = fBi;
        this.sBi = sBi;

        //shoulders

        this.fSh = fSh;
        this.sSh = sSh;
        this.tSh = tSh;

        //legs

        this.fLegs = fLegs;
        this.sLegs = sLegs;
        this.tLegs = tLegs;
        this.foLegs = foLegs;
        this.fiLegs = fiLegs;
        this.siLegs = siLegs;

        //chest

        this.fChest = fChest;
        this.sChest = sChest;

        //abs

        this.fAbs = fAbs;
        this.sAbs = sAbs;
        this.tAbs = tAbs;

        //back

        this.fBack = fBack;
        this.sBack = sBack;






    }
}
