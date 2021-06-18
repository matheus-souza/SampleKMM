import shared

enum State {
    case loading
    case success(ComicModel)
    case error
}
