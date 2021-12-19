package com.cn210.benchmark;

import java.io.IOException;

public interface Menu {
    public void onHomeButtonClick() throws IOException;
    public void onSystemButtonClick() throws IOException;
    public void onAboutButtonClick() throws IOException;
    public void onSRCButtonClick();
}
