class DiagonalDominatingError(Exception):
    def __init__(self: Exception, message: str):
        super().__init__(message)