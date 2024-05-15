package org.stypox.dicio.ui.main

/**
 * This is almost symmetrical to [VoskManagerState], except that there are no implementation-defined
 * fields. For this reason, if in the future another STT engine will be used, this class and the
 * whole UI layer could be kept the same.
 */
sealed class SttUiState {
    /**
     * Does not have a counterpart in [VoskManagerState] and will therefore never be generated by
     * [VoskManager]. In fact, this is used directly in the UI layer, since permission checks can
     * only be done there.
     */
    data object NoMicrophonePermission : SttUiState()

    /**
     * The model is not present on disk, neither in unzipped and in zipped form.
     */
    data object NotDownloaded : SttUiState()

    data class Downloading(
        val currentBytes: Long,
        val totalBytes: Long,
    ) : SttUiState()

    data class ErrorDownloading(
        val throwable: Throwable
    ) : SttUiState()

    data object Downloaded : SttUiState()

    /**
     * Vosk models are distributed in Zip files that need unzipping to be ready.
     */
    data class Unzipping(
        val currentBytes: Long,
        val totalBytes: Long,
    ) : SttUiState()

    data class ErrorUnzipping(
        val throwable: Throwable
    ) : SttUiState()

    /**
     * The model is present on disk, but was not loaded in RAM yet.
     */
    data object NotLoaded : SttUiState()

    /**
     * The model is being loaded, and [thenStartListening] indicates whether once loading is
     * finished, the STT should start listening right away.
     */
    data class Loading(
        val thenStartListening: Boolean
    ) : SttUiState()

    data class ErrorLoading(
        val throwable: Throwable
    ) : SttUiState()

    /**
     * The model is ready in RAM, and can start listening at any time.
     */
    data object Loaded : SttUiState()

    /**
     * The model is listening.
     */
    data object Listening : SttUiState()
}
