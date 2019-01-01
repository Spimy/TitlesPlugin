package dev.spimy.titles.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.spimy.titles.Main;

import com.google.common.base.Charsets;

public class TitlesConfig {

	private Main main;
	
	public TitlesConfig(Main main) {
		this.main = main;
		
		this.titleFile = new File(this.main.getDataFolder(), "titles.yml");
        if (!this.titleFile.exists()) {
            try {
                this.titleFile.getParentFile().mkdirs();
                this.titleFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.title = YamlConfiguration.loadConfiguration(this.titleFile);
        
	}
	
	private File titleFile;
    private FileConfiguration title;
    
    public FileConfiguration getTitleConfig() {
        return title;
    }

    public void saveTitleConfig() {
        try {
            this.title.save(this.titleFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void reloadTitleConfig() {
    	title = YamlConfiguration.loadConfiguration(titleFile);
		InputStream defItemsConfigStream = main.getResource("titles.yml");
		if (defItemsConfigStream != null) {
			title.setDefaults(
					YamlConfiguration.loadConfiguration(new InputStreamReader(defItemsConfigStream, Charsets.UTF_8)));
		}
	}
	
}
