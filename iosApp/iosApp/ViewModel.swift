class ViewModel: ObservableObject {
    let getLatesteComicUseCase = GetLatestComicUseCase(xkcdApi: XkcdApi())
        
    @Published var comic = State.loading
        
    init() {
        self.comic = .loading
        getLatestComicUseCase.run { fetchedComic, error in
            if fetchedComic != nil {
                self.comic = .success(fetchedComic!)
            } else {
                self.comic = .error
            }
        }
    }
}