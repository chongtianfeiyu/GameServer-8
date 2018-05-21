package com.game.stzb;

import com.game.stzb.Model.HeroEntity;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class htmlCreator {
    public static String getRandomHeroHtml(List<HeroEntity> mHeroEntities) {
        String html1 = null;
        html1 = readToString("config/html_random");
        String html2 = readToString("config/html_random2");
        StringBuilder mBuilder = new StringBuilder();
        for (int mI = 0; mI < mHeroEntities.size(); mI++) {
            HeroEntity mEntity = mHeroEntities.get(mI);
            String temp = html2.replace("heroid", String.valueOf(mEntity.getId()));
            temp = temp.replace("heroname", mEntity.getName());
            temp = temp.replace("herocontory", mEntity.getContory());
            temp = temp.replace("herocost", String.valueOf(mEntity.getCost()));
            temp = temp.replace("heroquality", String.valueOf(mEntity.getQuality()));
            temp = temp.replace("herotype", mEntity.getType());
            mBuilder.append(temp);
        }
        html1 = html1.replace("&herobody", mBuilder.toString());
        return html1;
    }

    public static String readToString(String fileName) {
        try {
            InputStream mInputStream = Resources.getResourceAsStream(fileName);
            byte[] mBytes = new byte[mInputStream.available()];
            mInputStream.read(mBytes);
            return new String(mBytes, "utf-8");
        } catch (IOException mE) {
            mE.printStackTrace();
        }
        return "";
    }
}
