package main;

import objects.OBJ_Boots;
import objects.OBJ_Door;
import objects.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Boots();
        gp.obj[0].worldX = 28 * gp.tileSize;
        gp.obj[0].worldY = 14 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 28 * gp.tileSize;
        gp.obj[1].worldY = 17 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Key();
        gp.obj[3].worldX = 30 * gp.tileSize;
        gp.obj[3].worldY = 30 * gp.tileSize;

    }
}
