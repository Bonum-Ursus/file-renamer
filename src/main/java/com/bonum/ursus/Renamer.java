package com.bonum.ursus;

import java.io.File;

public class Renamer {
    public static void main(String[] args) {
        File rootFolder = new File("c:\\Users\\Viktor\\Desktop\\GDZ_life\\");
        File[] subFolders = rootFolder.listFiles();
        new Renamer().renameFile(subFolders);
    }

    private void renameFile(File[] subFolders) {
        for (int i = 0; i < subFolders.length; i++) {
            if (subFolders[i].isDirectory())
                renameFile(subFolders[i].listFiles());
            if (subFolders[i].isFile()) {
                String[] splitedFilePath = subFolders[i].getAbsolutePath().split("\\\\");
                StringBuilder newFilePath = new StringBuilder();
                newFilePath.append(subFolders[i].getParent())
                        .append("\\")
                        .append(splitedFilePath[splitedFilePath.length - 4])
                        .append("_")
                        .append(splitedFilePath[splitedFilePath.length - 3])
                        .append("_")
                        .append(splitedFilePath[splitedFilePath.length - 2])
                        .append("." + splitedFilePath[splitedFilePath.length - 1].split("\\.")[1]);
                File newFile = new File(newFilePath.toString());
                if (newFile.exists()) {
                    newFile = new Renamer().ifExists(newFile, 2);
                }
                subFolders[i].renameTo(newFile);
                System.out.println(newFile.getAbsolutePath());
            }
        }
    }
    private File ifExists(File newFile, int count){
        String[] sNewFile = newFile.getAbsolutePath().split("\\.");
        newFile = new File(sNewFile[0] + "_" + count + "." + sNewFile[1]);
        if(newFile.exists())
            new Renamer().ifExists(newFile, ++count);
        return newFile;
    }
}
