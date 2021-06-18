import SwiftUI
import shared

struct ContentView: View {
 
    @ObservedObject private(set) var viewModel = ViewModel()
    
    var body: some View {
        comicView()
    }
    
    private func comicView() -> some View {
        switch viewModel.comic {
        case .loading:
            return AnyView(Text("Loading"))
        case .error:
            return AnyView(Text("Error"))
        case .success(let fetchedComic):
            return AnyView(
                VStack {
                    Text(fetchedComic.title)
                    Image(systemName: "")
                        .data(url: URL(string: fetchedComic.imageUrl)!)
                    Button("reload") {
                        viewModel.updateComic()
                    }
                }
            )
        }
    }
}

extension Image {
    func data(url: URL) -> Self {
        if let data = try? Data(contentsOf: url) {
            return Image(uiImage: UIImage(data: data)!)
                .resizable()
        }
        return self
            .resizable()
    }
}
