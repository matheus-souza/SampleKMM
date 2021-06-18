//
//  ViewModel.swift
//  iosApp
//
//  Created by Thiago Moraes on 18/06/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared


class ViewModel: ObservableObject {
    let getLatesteComicUseCase = GetLatestComicUseCase(xkcdApi: XkcdApi())
        
    @Published var comic: State = .loading
        
    init() {
        comic = .loading
        updateComic()
    }
    
    func updateComic() {
        comic = .loading
        getLatesteComicUseCase.run { fetchedComic, error in
            if fetchedComic != nil {
                self.comic = .success(fetchedComic!)
            } else {
                self.comic = .error
            }
        }
    }
}
