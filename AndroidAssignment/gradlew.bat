<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context=".SignUpFragment">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/sign_up_main_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingBottom="16dp"
        android:clickable="true"
        android:focusable="true"
        android:focusableInTouchMode="true">

        <com.google.android.material.textview.MaterialTextView
            android:id="@+id/text_sign_in"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/sign_up_text"
            android:textStyle="bold"
            android:textSize="24sp"
            android:padding="16dp"
            android:textAlignment="center"
            android:layout_marginTop="100dp"
            android:layout_marginStart="16dp"
            android:layout_marginEnd="16dp"
            android:textColor="@android:color/background_dark"
            app:layout_constraintTop_toTopOf="parent"/>

        <com.google.android.material.textfield.TextInputLayout
            style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
            android:id="@+id/name_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="48dp"
            android:layout_marginStart="16dp"
            android:layout_marginEnd="16dp"
            app:layout_goneMarginBottom="16dp"
            app:boxBackgroundColor="@android:color/transparent"
            app:layout_constraintTop_toBottomOf="@id/text_sign_in">
            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:hint="@string/name_text"
                android:textColor="@android:color/background_dar