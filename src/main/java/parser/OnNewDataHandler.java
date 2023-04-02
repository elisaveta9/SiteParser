package parser;

import org.jetbrains.annotations.NotNull;

public interface OnNewDataHandler<T>{

    void OnNewData(@NotNull Object sender, @NotNull T e);
}