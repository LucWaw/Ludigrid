//
//  ToolbarIOS.swift
//  iosApp
//
//  Created by Lucas Bigeard on 22/05/2025.
//

import SwiftUI
import ComposeApp

class IOSNativeViewFactory: NativeViewFactory {
    static var shared = IOSNativeViewFactory()
    func createToolbarView(
        onShareClick: @escaping () -> Void,
        onDeleteClick: @escaping () -> Void,
        onAddCommentClick: @escaping () -> Void) -> UIViewController {
        let view = ToolbarIOS(share: onShareClick, delete: onDeleteClick, addComment: onAddCommentClick)
        
        return UIHostingController(rootView: view)
    }
    
}
struct ToolbarIOS: View {
    var share: () -> Void
    var delete: () -> Void
    var addComment: () -> Void

    var body: some View {
        VStack {
            Spacer()
            Divider()
            HStack {
                Spacer()
                Button(action: share) {
                    VStack {
                        Image(systemName: "square.and.arrow.up")
                        Text("Partager")
                            .font(.caption)
                    }
                }
                Spacer()
                Button(action: delete) {
                    VStack {
                        Image(systemName: "trash")
                        Text("Supprimer")
                            .font(.caption)
                    }
                }
                Spacer()
                Button(action: addComment) {
                    VStack {
                        Image(systemName: "text.bubble")
                        Text("Commenter")
                            .font(.caption)
                    }
                }
                Spacer()
            }
            .padding(.vertical, 8)
            .background(Color(.systemBackground))
        }
    }
}



