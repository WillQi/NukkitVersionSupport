package io.github.willqi.nvs.packets.v419;

import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.resourcepacks.ResourcePack;
import io.github.willqi.nvs.packets.ConvertedProtocolPacket;

public class ResourcePacksInfoPacketV419 extends DataPacket implements ConvertedProtocolPacket {

    public boolean mustAccept;
    public boolean scripting;
    public ResourcePack[] behaviourPackEntries;
    public ResourcePack[] resourcePackEntries;

    @Override
    public byte pid() {
        return ProtocolInfo.RESOURCE_PACKS_INFO_PACKET;
    }

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        this.putBoolean(mustAccept);
        this.putBoolean(scripting);

        encodePacks(resourcePackEntries);
        encodePacks(behaviourPackEntries);
    }

    private void encodePacks(ResourcePack[] packs) {
        this.putLShort(packs.length);
        for (ResourcePack entry : packs) {
            this.putString(entry.getPackId().toString());
            this.putString(entry.getPackVersion());
            this.putLLong(entry.getPackSize());
            this.putString(""); // encryption key
            this.putString(""); // sub-pack name
            this.putString(""); // content identity
            this.putBoolean(false); // scripting
        }
    }

}