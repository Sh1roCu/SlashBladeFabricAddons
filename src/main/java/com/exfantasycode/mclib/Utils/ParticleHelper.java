package com.exfantasycode.mclib.Utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ParticleHelper {

    /**
     * 在给定的位置生成一个由粒子组成的立方体。
     *
     * @param level    世界实例（必须是服务端或客户端级别）
     * @param particle 粒子类型
     * @param pos      立方体中心点的位置
     * @param size     立方体的边长
     * @param density  每个维度上的粒子密度
     */
    public static void spawnParticleCube(Level level, SimpleParticleType particle, Vec3 pos, float size, int density) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return; // 只能在服务端或客户端级别生成粒子
        }

        float halfSize = size / 2f;
        for (int x = 0; x < density; x++) {
            for (int y = 0; y < density; y++) {
                for (int z = 0; z < density; z++) {
                    double xPos = pos.x - halfSize + (x * size) / (density - 1);
                    double yPos = pos.y - halfSize + (y * size) / (density - 1);
                    double zPos = pos.z - halfSize + (z * size) / (density - 1);

                    // 随机化粒子位置以避免规则模式
                    xPos += level.random.nextDouble() * size / density - size / (density * 2);
                    yPos += level.random.nextDouble() * size / density - size / (density * 2);
                    zPos += level.random.nextDouble() * size / density - size / (density * 2);

                    // 在服务端生成粒子
                    serverLevel.sendParticles(particle, xPos, yPos, zPos, 1, 0, 0, 0, 0);
                }
            }
        }
    }

    // 如果你需要在客户端生成粒子（例如用于即时视觉效果），可以使用下面的方法
    @Environment(EnvType.CLIENT)
    public static void spawnParticleCubeClient(ClientLevel clientLevel, SimpleParticleType particle, Vec3 pos, float size, int density) {
        float halfSize = size / 2f;
        for (int x = 0; x < density; x++) {
            for (int y = 0; y < density; y++) {
                for (int z = 0; z < density; z++) {
                    double xPos = pos.x - halfSize + (x * size) / (density - 1);
                    double yPos = pos.y - halfSize + (y * size) / (density - 1);
                    double zPos = pos.z - halfSize + (z * size) / (density - 1);

                    // 随机化粒子位置以避免规则模式
                    xPos += clientLevel.random.nextDouble() * size / density - size / (density * 2);
                    yPos += clientLevel.random.nextDouble() * size / density - size / (density * 2);
                    zPos += clientLevel.random.nextDouble() * size / density - size / (density * 2);

                    // 在客户端生成粒子
                    clientLevel.addParticle(particle, xPos, yPos, zPos, 0, 0, 0);
                }
            }
        }
    }

    /**
     * 在给定的位置生成一个由粒子组成的空心立方体。
     *
     * @param level    世界实例（必须是服务端或客户端级别）
     * @param particle 粒子类型
     * @param pos      立方体中心点的位置
     * @param size     立方体的边长
     * @param density  每个维度上的粒子密度
     */
    public static void spawnHollowParticleCube(Level level, SimpleParticleType particle, Vec3 pos, float size, int density) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return; // 只能在服务端或客户端级别生成粒子
        }

        float halfSize = size / 2f;
        float step = size / (density - 1);

        for (int x = 0; x < density; x++) {
            for (int y = 0; y < density; y++) {
                // Front face
                double xPos = pos.x - halfSize + x * step;
                double yPos = pos.y - halfSize + y * step;
                double zPos = pos.z - halfSize;
                addRandomizedParticle(serverLevel, particle, xPos, yPos, zPos, size, density);

                // Back face
                zPos = pos.z + halfSize;
                addRandomizedParticle(serverLevel, particle, xPos, yPos, zPos, size, density);
            }
        }

        for (int x = 0; x < density; x++) {
            for (int z = 0; z < density; z++) {
                // Top face
                double xPos = pos.x - halfSize + x * step;
                double yPos = pos.y + halfSize;
                double zPos = pos.z - halfSize + z * step;
                addRandomizedParticle(serverLevel, particle, xPos, yPos, zPos, size, density);

                // Bottom face
                yPos = pos.y - halfSize;
                addRandomizedParticle(serverLevel, particle, xPos, yPos, zPos, size, density);
            }
        }

        for (int y = 0; y < density; y++) {
            for (int z = 0; z < density; z++) {
                // Left face
                double xPos = pos.x - halfSize;
                double yPos = pos.y - halfSize + y * step;
                double zPos = pos.z - halfSize + z * step;
                addRandomizedParticle(serverLevel, particle, xPos, yPos, zPos, size, density);

                // Right face
                xPos = pos.x + halfSize;
                addRandomizedParticle(serverLevel, particle, xPos, yPos, zPos, size, density);
            }
        }
    }

    private static void addRandomizedParticle(ServerLevel serverLevel, SimpleParticleType particle, double posX, double posY, double posZ, float size, int density) {
        // 随机化粒子位置以避免规则模式
        posX += serverLevel.random.nextDouble() * size / density - size / (density * 2);
        posY += serverLevel.random.nextDouble() * size / density - size / (density * 2);
        posZ += serverLevel.random.nextDouble() * size / density - size / (density * 2);

        // 在服务端生成粒子
        serverLevel.sendParticles(particle, posX, posY, posZ, 0, 0, 0, 0, 0);
    }

    // 如果你需要在客户端生成粒子（例如用于即时视觉效果），可以使用下面的方法
    @Environment(EnvType.CLIENT)
    public static void spawnHollowParticleCubeClient(ClientLevel clientLevel, SimpleParticleType particle, Vec3 pos, float size, int density) {
        float halfSize = size / 2f;
        float step = size / (density - 1);

        for (int x = 0; x < density; x++) {
            for (int y = 0; y < density; y++) {
                // Front face
                double xPos = pos.x - halfSize + x * step;
                double yPos = pos.y - halfSize + y * step;
                double zPos = pos.z - halfSize;
                addRandomizedParticleClient(clientLevel, particle, xPos, yPos, zPos, size, density);

                // Back face
                zPos = pos.z + halfSize;
                addRandomizedParticleClient(clientLevel, particle, xPos, yPos, zPos, size, density);
            }
        }

        for (int x = 0; x < density; x++) {
            for (int z = 0; z < density; z++) {
                // Top face
                double xPos = pos.x - halfSize + x * step;
                double yPos = pos.y + halfSize;
                double zPos = pos.z - halfSize + z * step;
                addRandomizedParticleClient(clientLevel, particle, xPos, yPos, zPos, size, density);

                // Bottom face
                yPos = pos.y - halfSize;
                addRandomizedParticleClient(clientLevel, particle, xPos, yPos, zPos, size, density);
            }
        }

        for (int y = 0; y < density; y++) {
            for (int z = 0; z < density; z++) {
                // Left face
                double xPos = pos.x - halfSize;
                double yPos = pos.y - halfSize + y * step;
                double zPos = pos.z - halfSize + z * step;
                addRandomizedParticleClient(clientLevel, particle, xPos, yPos, zPos, size, density);

                // Right face
                xPos = pos.x + halfSize;
                addRandomizedParticleClient(clientLevel, particle, xPos, yPos, zPos, size, density);
            }
        }
    }

    private static void addRandomizedParticleClient(ClientLevel clientLevel, SimpleParticleType particle, double posX, double posY, double posZ, float size, int density) {
        // 随机化粒子位置以避免规则模式
        posX += clientLevel.random.nextDouble() * size / density - size / (density * 2);
        posY += clientLevel.random.nextDouble() * size / density - size / (density * 2);
        posZ += clientLevel.random.nextDouble() * size / density - size / (density * 2);

        // 在客户端生成粒子
        clientLevel.addParticle(particle, posX, posY, posZ, 0, 0, 0);
    }
}
