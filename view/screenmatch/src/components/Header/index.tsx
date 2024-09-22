import Categories from './Categories';
import styles from './Header.module.scss';
import Logo from "./Logo";
import Profile from './Profile';
import Search from './Search';


export default function Header() {
  return (
    <header className={ styles.container }>
      <Logo />
      <div className={ styles.info }>
        <Categories />
        <Search />
        <Profile />
      </div>
    </header>
  );
}