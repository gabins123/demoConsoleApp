package demoConsoleApp.Core;

import java.util.List;

public interface IConfigObserver<T> {
    void OnRefresh(List<T> records );
}
