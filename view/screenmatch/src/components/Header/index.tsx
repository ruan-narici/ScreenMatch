import { useRecoilValue } from 'recoil';
import Categories from './Categories';
import styles from './Header.module.scss';
import Logo from './Logo';
import Profile from './Profile';
import Search from './Search';
import { isHome } from '../../state/atom';


export default function Header() {
  const isHomeValue = useRecoilValue(isHome);

  return (
    <header className={ styles.container }>
      <Logo />
      <div className={ styles.info }>
        { isHomeValue && <Categories /> }
        { isHomeValue && <Search /> }
        <Profile />
      </div>
    </header>
  );
}