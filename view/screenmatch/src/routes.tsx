import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import NotFound from './pages/NotFound';
import Header from './components/Header';
import Serie from './pages/Serie';
import { RecoilRoot } from 'recoil';

export default function AppRouter() {
  return (
    <RecoilRoot>
      <Header />
      <main>
        <Router>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/serie/:id" element={<Serie />} />
            <Route path="/*" element={<NotFound />} />
          </Routes>
        </Router>
      </main>
    </RecoilRoot>
  );
}