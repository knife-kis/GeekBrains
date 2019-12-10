package com.lesson01.tarnovskiy.MammalsAndRobot;

import com.lesson01.tarnovskiy.Track.Track;

/**
 * @author Tarnovskiy Maksim
 */
public interface Movement {
    void run(Track track);
    void jump(Track track);
}
