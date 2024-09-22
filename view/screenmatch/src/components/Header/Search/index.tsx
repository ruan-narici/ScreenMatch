import IconSearch from '@/assets/img/icon_search.svg';
import styles from './Search.module.scss';


export default function Search() {
  return (
    <>
      <div className={ styles.container }>
        <input className={ styles.container__input } placeholder='Buscar no ScreenMatch' type="text" />
        <img src={ IconSearch } className={ styles.container__icon } />
      </div>
    </>
  );
}