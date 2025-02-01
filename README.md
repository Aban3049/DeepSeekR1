# ✨ Kotlin Multiplatform LLM Chat with Ollama

This is a **Kotlin Multiplatform (KMP)** project demonstrating how to integrate a **locally running Large Language Model (LLM) with Ollama** into a KMP application that supports **Android, iOS, and Desktop** platforms. The project showcases key features like chat history management with Room, user preferences storage with DataStore, and support for both light and dark themes.

## 🚀 Features
- ✅ **Local LLM Integration**: Chat with locally running **DeepSeek** models on Ollama.
- 📱 **Multiplatform Support**: Works seamlessly on **Android, iOS, and Desktop**.
- 💾 **Chat History**: Uses **Room Database** (for Android & Desktop) to persist chat history.
- ⚙️ **User Preferences**: Uses **DataStore** to save theme and model preferences.
- 🎨 **Theming**: Supports **light and dark themes**.
- 🧠 **Example Models**:
  - 🔹 `deepseek-r1:1.5b`
  - 🔹 `deepseek-coder:1.3b`

## 🛠 Tech Stack
- 🏗 **Kotlin Multiplatform** (KMP)
- 🎨 **Jetpack Compose** (Android UI)
- 🍏 **Jetpack Compose** (iOS UI)
- 💻 **Compose for Desktop**
- 🌐 **Ktor** (for networking & communicating with Ollama API)
- 🗄 **Room Database** (for chat history on Android & Desktop)
- 🛍 **DataStore** (for storing preferences)
- 🧠 **Ollama** (for running local LLMs)
 
![Screenshot 2025-02-01 at 11 11 33 PM](https://github.com/user-attachments/assets/0e75efa9-8451-4145-927e-9fce70cb5c01)

## 📌 Getting Started
### 📋 Prerequisites
1. Install [Ollama](https://ollama.com/) and set up the required models:
   ```sh
   ollama pull deepseek-r1:1.5b
   ollama pull deepseek-coder:1.3b
   ```
2. Ensure you have the necessary tools installed for KMP development:
   - 📱 **Android Studio** (for Android & Desktop)
   - 🍏 **Xcode** (for iOS)

### ▶️ Running the Project
#### 📲 Android
1. Open the project in **Android Studio**.
2. Select an Android device/emulator and run the app.

#### 🍏 iOS
1. Open the `iosApp` project in **Xcode**.
2. Run on an **iOS Simulator** or a physical device.

#### 💻 Desktop
1. Use `gradle` to build and run the Desktop app:
   ```sh
   ./gradlew run
   ```

## ⚙️ Configuration
- 🔧 **Modify the Ollama server URL if necessary** in the Ktor client configuration.
- 📝 **Customize available models in the UI as needed.**

## 📜 License
This project is licensed under the **MIT License**. Feel free to modify and use it in your projects!

---
### 💡 Contributions & Feedback
Feel free to contribute, report issues, or suggest improvements via **GitHub Issues** or **Pull Requests**. Your feedback is welcome! 🚀
