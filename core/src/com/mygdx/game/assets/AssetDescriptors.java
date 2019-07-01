package com.mygdx.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class AssetDescriptors {
    private static final AssetDescriptor iceDescriptor = new AssetDescriptor<Texture>(AssetsNames.ice, Texture.class);
    private static final AssetDescriptor groundDescriptor = new AssetDescriptor<Texture>(AssetsNames.ground, Texture.class);
    private static final AssetDescriptor lavaDescriptor = new AssetDescriptor<Texture>(AssetsNames.lava, Texture.class);
    private static final AssetDescriptor stoneDescriptor = new AssetDescriptor<Texture>(AssetsNames.stone, Texture.class);

    public static List<AssetDescriptor<Texture>> assetList(){
        List list = new ArrayList<AssetDescriptor<Texture>>();
        list.add(iceDescriptor);
        list.add(groundDescriptor);
        list.add(lavaDescriptor);
        list.add(stoneDescriptor);

        return list;
    }




    private AssetDescriptors(){}
}
