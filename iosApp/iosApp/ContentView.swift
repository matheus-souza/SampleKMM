import SwiftUI
import shared

struct ContentView: View {
 
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        comicView()
    }
    --
    private func comicView() -> some View {
        switch viewModel.comic {
        case .loading:
            return AnyView(Text("Loading"))
        case .result(let comic):
            return AnyView(VStack {
                Text(comic.title)
                RemoteImage(url: comic.img)
            })
        case .error:
            return AnyView(Text("Error"))
        }
    }
}