package com.gbas.ms.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.gbas.ms.MSGame;
import com.gbas.ms.utils.LRUCache;


/**
 * A service that manages the sound effects.
 */
public class SoundManager implements Disposable {
    public void playVoice(int voiceType, int voiceValue) {

    }

    /**
     * The available sound files.
     */
    public enum MSGameSound {
        CLICK("sound/click.wav");

        private final String fileName;

        private MSGameSound(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }

    /**
     * The volume to be set on the sound.
     */
    private float volume = 1f;

    /**
     * Whether the sound is enabled.
     */
    private boolean enabled = true;

    /**
     * The sound cache.
     */
    private final LRUCache<MSGameSound, Sound> soundCache;
    private final LRUCache<String, Sound> soundNames;

    /**
     * Creates the sound manager.
     */
    public SoundManager() {
        soundCache = new LRUCache<MSGameSound, Sound>(10);
        soundCache.setEntryRemovedListener(new LRUCache.CacheEntryRemovedListener<MSGameSound, Sound>() {
            @Override
            public void notifyEntryRemoved(MSGameSound key, Sound value) {
                Gdx.app.log(MSGame.LOG, "Disposing sound: " + key.name());
                value.dispose();
            }
        });
        soundNames = new LRUCache<String, Sound>(30);
        soundNames.setEntryRemovedListener(new LRUCache.CacheEntryRemovedListener<String, Sound>() {
            @Override
            public void notifyEntryRemoved(String key, Sound value) {
                Gdx.app.log(MSGame.LOG, "Disposing sound: " + key);
                value.dispose();

            }
        });
    }

    /**
     * Plays the specified sound.
     */
    public void play(MSGameSound sound) {
        // check if the sound is enabled
        if (!enabled) return;

        // try and get the sound from the cache
        Sound soundToPlay = soundCache.get(sound);
        if (soundToPlay == null) {
            FileHandle soundFile = Gdx.files.internal(sound.getFileName());
            soundToPlay = Gdx.audio.newSound(soundFile);
            soundCache.add(sound, soundToPlay);
        } else {
            Gdx.app.log(MSGame.LOG, "Reusando " + sound.name());
        }

        // play the sound
        Gdx.app.log(MSGame.LOG, "Playing sound: " + sound.name());
        soundToPlay.play(volume);
    }

    /**
     * Plays the specified sound.
     */
    public void play(String soundName) {
        // check if the sound is enabled
        if (!enabled) return;

        // try and get the sound from the cache
        Sound soundToPlay = soundNames.get(soundName);
        if (soundToPlay == null) {
            FileHandle soundFile = Gdx.files.internal(soundName);
            soundToPlay = Gdx.audio.newSound(soundFile);
            soundNames.add(soundName, soundToPlay);
        } else {
            Gdx.app.log(MSGame.LOG, "Reusando " + soundName);
        }

        // play the sound
        Gdx.app.log(MSGame.LOG, "Playing sound: " + soundName);
        soundToPlay.play(volume);
    }

    public void play(int voz, int...sonido) {
        final String vozDir[] = {"sounds/fidel", "sounds/siria", "sounds/dario", "sounds/chica1", "sounds/hector", "sounds/chica2"};

        int random = MathUtils.random(0, sonido.length - 1);
        int i = sonido[random];
        if (i > 0) {
            play(vozDir[voz] + "/" + i +".wav");
        }
    }

    /**
     * Sets the sound volume which must be inside the range [0,1].
     */
    public void setVolume(float volume) {
        Gdx.app.log(MSGame.LOG, "Adjusting sound volume to: " + volume);

        // check and set the new volume
        if (volume < 0 || volume > 1f) {
            throw new IllegalArgumentException("The volume must be inside the range: [0,1]");
        }
        this.volume = volume;
    }

    /**
     * Enables or disabled the sound.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // EntryRemovedListener implementation

     /**
     * Disposes the sound manager.
     */
    public void dispose() {
        Gdx.app.log(MSGame.LOG, "Disposing sound manager");
        for (Sound sound : soundCache.retrieveAll()) {
            sound.stop();
            sound.dispose();
        }
        for (Sound sound : soundNames.retrieveAll()) {
            sound.stop();
            sound.dispose();
        }
    }
}
