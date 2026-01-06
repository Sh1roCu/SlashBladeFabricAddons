package cn.sh1rocu.sfaddons.api.extension;

public interface IEntityListener {
    boolean isAddedToWorld();

    void onAddedToWorld();

    void onRemovedFromWorld();
}