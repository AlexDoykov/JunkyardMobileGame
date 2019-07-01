package com.mygdx.game.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public AssetManager manager;

    public Assets(){
        manager = new AssetManager();
    }

    public void load(){
        manager.load(AssetsNames.ground, Texture.class);
        manager.load(AssetsNames.ice, Texture.class);
        manager.load(AssetsNames.lava, Texture.class);
        manager.load(AssetsNames.stone, Texture.class);


    }

    public void dispose(){
        manager.dispose();
    }
}

