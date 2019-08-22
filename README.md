# CustomPassword

# Version
[![](https://jitpack.io/v/dionisiofilho/CustomPassword.svg)](https://jitpack.io/#dionisiofilho/CustomPassword)

# PasswordViewCustom

* Adicione isto em seu arquivo build.gradle (Project: name_project):

```sh
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
* Adicione ao build.gradle (Module: app):

 ```sh
dependencies {
        implementation 'com.github.dionisiofilho:CustomPassword:v1.0'
}
```
# minSdkVersion 
21 - Android "Lollipop" 

# Permissões
O PasswordView utiliza a permissão. (Opcional)
```
    <uses-permission android:name="android.permission.VIBRATE"/>
```
# Exemplos

<img src="https://imgur.com/a/s5j1wVh" alt="Home" width="350" height="550">

<img src="https://i.imgur.com/7lfLFLF.png" alt="Home" width="350" height="550">

<img src="https://i.imgur.com/tOj7aNx.png" alt="Home" width="350" height="550">


# Utilizando o PasswordViewCustom
```sh
 <com.dionisiofilho.passwordview.PasswordViewCustom
            android:layout_width="0dp"
            app:qtd_password="4"
            android:id="@+id/pvc_password"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
 ```  
O item app:qtd_password indica a quantidade de caracteres. Default : 4

# Funções:
  - error -> indica para o componente que apresenta um erro na senha.
  - cleanError -> limpa os erros apresentados no componente.
  - getTextPassowrd -> retorna a senha digitada pelo usuário no formato : String.
  - onFinishPassword -> Função executada após o usuário digitar a senha, conforme a quantidade fornecida.


License
----


