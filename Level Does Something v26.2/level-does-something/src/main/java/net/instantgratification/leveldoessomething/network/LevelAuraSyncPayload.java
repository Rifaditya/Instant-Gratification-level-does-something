// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.leveldoessomething.network;

import net.instantgratification.leveldoessomething.LevelDoesSomethingFabric;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record LevelAuraSyncPayload(int entityId, int auraLevel) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LevelAuraSyncPayload> TYPE = new CustomPacketPayload.Type<>(
            Identifier.fromNamespaceAndPath(LevelDoesSomethingFabric.MOD_ID, "aura_sync")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, LevelAuraSyncPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            LevelAuraSyncPayload::entityId,
            ByteBufCodecs.VAR_INT,
            LevelAuraSyncPayload::auraLevel,
            LevelAuraSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
