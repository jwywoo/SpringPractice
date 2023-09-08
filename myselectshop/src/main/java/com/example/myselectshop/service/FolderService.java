package com.example.myselectshop.service;

import com.example.myselectshop.dto.FolderResponseDto;
import com.example.myselectshop.entity.Folder;
import com.example.myselectshop.entity.User;
import com.example.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;
    public void addFolders(List<String> folderNames, User user) {
        List<Folder> currentFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);
        List<Folder> folderList = new ArrayList<>();
        for (String folderName: folderNames) {
            if (!isExistFolderName(folderName, currentFolderList)) {
                Folder newFolder = new Folder(folderName, user);
                folderList.add(newFolder);
            } else {
                throw new IllegalArgumentException("Duplicate folder name");
            }
        }

        folderRepository.saveAll(folderList);
    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        return folderList.stream().map(FolderResponseDto::new).toList();
    }

    private boolean isExistFolderName(String folderName, List<Folder> currentFolderList) {
        for (Folder existFolder: currentFolderList) {
            if(folderName.equals(existFolder.getName())) {
                return true;
            }
        }
        return false;
    }

}
